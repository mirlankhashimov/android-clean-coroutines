import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import com.mirlan.sandbox.R
import com.mirlan.sandbox.databinding.SalonFragmentImageBinding
import com.mirlan.sandbox.utils.Constants.IMG_BASE_URL
import com.mirlan.sandbox.utils.viewBinding

class ImageFragment : Fragment(R.layout.salon_fragment_image) {
    private val binding by viewBinding(SalonFragmentImageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUri = arguments?.getString(IMAGE_URI)
        binding.salonImage.load(IMG_BASE_URL + imageUri)
    }

    companion object {
        const val IMAGE_URI = "image_uri"
    }
}