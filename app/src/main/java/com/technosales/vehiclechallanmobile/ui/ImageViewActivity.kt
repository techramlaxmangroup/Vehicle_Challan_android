package com.technosales.vehiclechallanmobile.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.technosales.vehiclechallanmobile.R
import com.technosales.vehiclechallanmobile.databinding.ActivityImageViewBinding
import com.technosales.vehiclechallanmobile.utils.TextConfig.EXTRA_IMAGE_URL

class ImageViewActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ImageViewActivity"
    }

    private lateinit var mBinding: ActivityImageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_view)
        mBinding.clickHandler = ImageViewActivityClickHandler(this)

        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        mBinding.imageUrl = imageUrl
    }

    inner class ImageViewActivityClickHandler(val context: Context) {

        fun onBackClicked(view: View) {
            onBackPressed()
        }
    }
}