import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thetrackgoals.databinding.IntroItemPageBinding
import com.example.thetrackgoals.data.models.IntroView

class ViewPagerAdapter(private val introViews: List<IntroView>) : RecyclerView.Adapter<ViewPagerAdapter.IntroViewHolder>() {

    class IntroViewHolder(private val binding: IntroItemPageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(introView: IntroView) {
            binding.ivImageIntro.setImageResource(introView.imageId)
            binding.tvDescriptionIntro.text = introView.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val binding = IntroItemPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IntroViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return introViews.size
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(introViews[position])
    }
}
