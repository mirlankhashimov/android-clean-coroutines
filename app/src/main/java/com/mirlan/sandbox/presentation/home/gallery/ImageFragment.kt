import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.api.load
import com.mirlan.sandbox.R
import com.mirlan.sandbox.utils.Constants.IMG_BASE_URL
import kotlinx.android.synthetic.main.salon_fragment_image.*

class ImageFragment : Fragment(R.layout.salon_fragment_image) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUri = arguments?.getString(IMAGE_URI)
        salon_image.load(IMG_BASE_URL + imageUri)
    }

    companion object {
        const val IMAGE_URI = "image_uri"
    }
}