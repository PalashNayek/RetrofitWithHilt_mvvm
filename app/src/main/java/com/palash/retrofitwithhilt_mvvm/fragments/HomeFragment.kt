package com.palash.retrofitwithhilt_mvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.palash.retrofitwithhilt_mvvm.databinding.FragmentHomeBinding
import com.palash.retrofitwithhilt_mvvm.models.add_new_cart.AddNewCartRequest
import com.palash.retrofitwithhilt_mvvm.models.update_cart.request.UpdateCartRequest
import com.palash.retrofitwithhilt_mvvm.utils.TokenManager
import com.palash.retrofitwithhilt_mvvm.view_models.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.palash.retrofitwithhilt_mvvm.models.update_cart.request.Product

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel by viewModels<LoginViewModel>()
    private val authUserViewModel by viewModels<GetAuthUserViewModel>()
    private val refreshTokenViewModel by viewModels<RefreshTokenViewModel>()
    private val allProductsViewModel by viewModels<AllProductsViewModel>()
    private val singleProductViewModel by viewModels<SingleProductViewModel>()
    private val searchViewModel by viewModels<SearchViewModel>()
    private val limitAndSkipProductViewModel by viewModels<LimitAndSkipProductViewModel>()
    private val productCategoryViewModel by viewModels<ProductCategoryViewModel>()
    private val addNewProductCategory by viewModels<AddProductViewModel>()
    private val updateProductViewModel by viewModels<UpdateProductViewModel>()
    private val deleteProductViewModel by viewModels<DeleteProductViewModel>()
    private val addToCartViewModel by viewModels<AddToCartViewModel>()
    private val updateCartViewModel by viewModels<UpdateCartViewModel>()

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Login...................................
        /*loginViewModel.login(LoginRequest("emilyspass", "emilys"))
        loginViewModel.loginData.observe(viewLifecycleOwner, Observer {
            //binding.txt.text = it.data!!.token
            tokenManager.saveToken(it.data!!.token)
        })*/


        //Get auth user.........................
        /*authUserViewModel.getAuthUser()
        authUserViewModel.authUserLiveData.observe(viewLifecycleOwner, Observer {
            binding.txt.text = it.data!!.firstName
        })*/

        //refresh token.........................
        /*refreshTokenViewModel.refreshToken(RefreshTokenRequest("60"))
        refreshTokenViewModel.refreshTokenLiveData.observe(
            viewLifecycleOwner,
            Observer { refreshToken ->
                binding.txt.text = refreshToken.data!!.image
                tokenManager.saveToken(refreshToken.data.token)
            })*/

        //Get all products.........................
        /*allProductsViewModel.getAllProduct()
        allProductsViewModel.getAllProductLiveData.observe(viewLifecycleOwner, Observer {
            binding.txt.text = it.data.toString()
        })*/

        //Single product.............................
        /*singleProductViewModel.singleProduct(1)
        singleProductViewModel.singleProductLiveData.observe(viewLifecycleOwner, Observer {
            binding.txt.text=it.data!!.title
        })*/

        //Search product item........................
        /*searchViewModel.searchProduct("Cooking Oil")
        searchViewModel.searchLiveData.observe(viewLifecycleOwner, Observer {
            binding.txt.text=it.data.toString()
        })*/

        //Limit and Skip product.................
        /*val selectProductItem = mutableListOf<String>()

        selectProductItem.add("title")
        selectProductItem.add("price")

        val stringBuilder = StringBuilder()
        for (values in selectProductItem){
            stringBuilder.append(values)
            stringBuilder.append(",")
        }

        val select = stringBuilder.removeSuffix(",")
        //Log.d("SelectValue1", "$select1")
        limitAndSkipProductViewModel.limitAndSkipProduct(10, 0, select)
        limitAndSkipProductViewModel.limitAndSkipProductLiveData.observe(
            viewLifecycleOwner,
            Observer {
                binding.txt.text = it.data!!.products.toString()
            })*/

        //Get product category...................
        /*productCategoryViewModel.getProductCategory()
        productCategoryViewModel.productCategoryLiveData.observe(viewLifecycleOwner, Observer {
            binding.txt.text = it.data.toString()
        })*/

        //Add new product.................
        /* addNewProductCategory.addNewProduct(AddNewProductRequest("200000","HONDA BIKE"))
         addNewProductCategory.addNewProductLiveData.observe(viewLifecycleOwner, Observer {
             binding.txt.text = it.data?.title
         })*/

        //update product.............
        /*binding.progressBar.isVisible = true
        updateProductViewModel.updateProduct(100)
        updateProductViewModel.updateProductLiveData.observe(viewLifecycleOwner, Observer {

            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is NetworkResult.Success -> {
                    binding.progressBar.isVisible = false
                    binding.txt.text = it.data.toString()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.isVisible = false
                }
            }
        })*/

        //Delete product................................
        /*deleteProductViewModel.deleteProduct(1)
        deleteProductViewModel.deleteProductLiveData.observe(viewLifecycleOwner, Observer {
            binding.txt.text = it.data.toString()
        })*/

        //Add to cart
        /*addToCartViewModel.addToCart(AddNewCartRequest(products = Product(1,1),1))
        addToCartViewModel.addToCardLiveData.observe(viewLifecycleOwner, Observer {
            binding.txt.text = it.data.toString()
        })*/

        //update cart
        updateCartViewModel.updateCart(1, Product(1,1))
        updateCartViewModel.updateCartLiveData.observe(viewLifecycleOwner, Observer {
            binding.txt.text = it.data.toString()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}