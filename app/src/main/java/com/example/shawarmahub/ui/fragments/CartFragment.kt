package com.example.shawarmahub.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shawarmahub.R
import com.example.shawarmahub.adapters.CartAdapter
import com.example.shawarmahub.databinding.FragmentCartBinding
import com.example.shawarmahub.db.OrderDatabase
import com.example.shawarmahub.db.model.Order
import com.example.shawarmahub.repository.Repository
import com.example.shawarmahub.ui.viewModel.MainViewModel
import com.example.shawarmahub.ui.viewModel.ViewModelFactory
import com.example.shawarmahub.utils.Constants.Companion.AES_KEY
import com.example.shawarmahub.utils.Constants.Companion.ID
import com.example.shawarmahub.utils.Constants.Companion.PUBLIC_KEY
import com.example.shawarmahub.utils.randomStringGenerator.generateString
import com.google.gson.Gson
import com.opay.account.core.LoginTask
import com.opay.account.core.PayTask
import com.opay.account.iinterface.LoginResultCallback
import com.opay.account.iinterface.PayResultCallback
import com.opay.account.iinterface.ResultStatus
import com.opay.account.model.LoginResult
import com.opay.account.model.OrderInfo

const val REQUEST_CODE = 200

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: MainViewModel
    lateinit var adapter: CartAdapter
    var orders = mutableListOf<Order?>()
    private var ref: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        /** dao instance**/
        val dao = OrderDatabase.invoke(requireContext()).getOrderDao()

        /** repository instance**/
        val repository = Repository(dao)

        /** viewModel instance**/
        val factory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**continue shopping**/
        binding.continueShopping.setOnClickListener {
            findNavController().navigate(R.id.shawarmaMenuFragment)
        }

        val bundle = CartFragmentArgs.fromBundle(requireArguments())
        val data = bundle.order
        orders.add(data)

        /***setup adapter**/
        adapter = CartAdapter(orders, viewModel)
        binding.cartRv.adapter = adapter

        viewModel.allOrders().observe(viewLifecycleOwner, Observer {
            adapter.setOrders(it)
        })
        binding.cartRv.layoutManager = LinearLayoutManager(requireContext())

        /***display total price**/
        viewModel.totalPrice().observe(viewLifecycleOwner, Observer {
            if (it != null) binding.totalPrice.text = it.toString()
        })



        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        /***checkout and make payment**/
        binding.checkout.setOnClickListener {
            ref = generateString()
            loginTask()
        }


    }


    fun loginTask() = LoginTask(PUBLIC_KEY, ID, AES_KEY).login(
        context,
        object : LoginResultCallback {
            override fun onResult(status: LoginResult?) {
                Log.i("TAG", "onResult: ${Gson().toJson(status)}")
                //if(REQUEST_CODE == status?.getStatus()?.code){
                /***
                 * status returned "SERVER ERROR"
                 * sent a mail but got no reply
                 * **/
                takeOrder()
                //}
            }

        })

    fun takeOrder() {
        val amt = (binding.totalPrice.text.toString().toInt()).toDouble()
        val currency = "NGN"
        val merchantName = "FredOsuala"
        val merchantUserId = ID
        val reference = ref
        val publicKey = PUBLIC_KEY
        val description = "sharwama"

        val order = OrderInfo()
        order.amount = amt
        order.currency = currency
        order.description = description
        order.merchantName = merchantName
        order.merchantUserId = merchantUserId
        order.publicKey = publicKey
        order.reference = reference

        PayTask(order).pay(context, object : PayResultCallback {
            override fun onResult(status: ResultStatus?) {
                Log.i("TAG", "onResult: ${Gson().toJson(status)}")
                if (status?.code == REQUEST_CODE) {
                    Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}