package com.example.wifimodule.base.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.wifimodule.R
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.SharedPrefs
import com.example.wifimodule.databinding.LayoutProgressbarBinding
import com.example.wifimodule.utils.LocaleHelper
import com.example.wifimodule.utils.LocaleManager
import com.example.wifimodule.utils.PermissionCallBack
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<DB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {
    private var dialog: Dialog? = null
    private var dialogBinding: LayoutProgressbarBinding? = null
    private var permissionCallBack: PermissionCallBack? = null
    private var permissions = ArrayList<String>()

    val binding: DB by lazy {
        val persistentClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<DB>
        val inflateMethod = persistentClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        inflateMethod.invoke(null, layoutInflater) as DB
    }

    protected val viewModel: VM by lazy {
        val persistentClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM>
        ViewModelProvider(this)[persistentClass]
    }

    open val isBackEnable: Boolean?
        get() = null

    open val toolbar: Toolbar?
        get() = null

    open val titleToolbar: String?
        get() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleHelper.onCreate(
            this,
            if (SharedPrefs(this).getLanguage().isEmpty()) "en" else SharedPrefs(this).getLanguage()
        )
        setContentView(binding.root)
        initToolbar(toolbar)
        initViewBinding()
        observeViewModel()
    }

    protected abstract fun observeViewModel()

    protected abstract fun initViewBinding()

    fun initToolbar(toolbar: Toolbar?) {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            initToolbarBack(isBackEnable)
            initToolbarTitle(titleToolbar)
        }
    }

    fun initToolbarBack(isEnable: Boolean? = false) {
        this.supportActionBar?.setHomeButtonEnabled(isEnable ?: false)
        this.supportActionBar?.setDisplayHomeAsUpEnabled(isEnable ?: false)
        this.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
    }

    fun initToolbarTitle(title: String?) {
//        viewModel.title.value = title
        if (this.supportActionBar != null && title?.isNotEmpty() == true) {
            this.supportActionBar?.title = title
            this.toolbar?.title = title
        }
    }

    /**
     * Display progress dialog on screen when this method call with true flag
     * Dismiss progress dialog if dialog isShowing and flag is false
     * @param b     Dialog display control flag
     */
    fun loadingDialog(b: Boolean?, message: String? = getString(R.string.loading)) {
        if ((b == true)) {
            if ((dialog == null)) {
                dialogBinding = LayoutProgressbarBinding.inflate(layoutInflater)
                dialog = AppCompatDialog(this/*, R.style.progress_bar_style*/)
                dialogBinding?.root?.let { dialog?.setContentView(it) }
                dialog?.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog?.setCancelable(false)
                dialog?.setCanceledOnTouchOutside(false)
            }
            dialogBinding?.message?.text = message
            dialog?.show()
        } else {
            if (dialog?.isShowing == true)
                dialog?.dismiss()
        }
    }

    fun requestPermission(
        permissions: ArrayList<String>,
        permissionCallBack: PermissionCallBack?
    ) {
        this.permissionCallBack = permissionCallBack
        this.permissions = permissions
        mPermissionResult.launch(permissions.toTypedArray())
    }

    private val mPermissionResult = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->

        result.forEach { actionMap ->
            val granted = actionMap.value
            val permission = actionMap.key
            if (!granted) {
                val neverAskAgain = !ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    permission
                )
                if (neverAskAgain) {
                    permissionCallBack?.onPermissionDisabled()
                } else {
                    permissionCallBack?.permissionDenied()
                }
            } else {
                permissionCallBack?.permissionGranted()
            }
            return@registerForActivityResult
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        LocaleManager.setLocale(this)
    }

}