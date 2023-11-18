import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.islami.Quran.quranContentViewModel

class QuranContentViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(quranContentViewModel::class.java)) {
            return quranContentViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
