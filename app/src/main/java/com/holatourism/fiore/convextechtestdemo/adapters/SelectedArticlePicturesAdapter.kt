package  com.holatourism.fiore.convextechtestdemo.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.holatourism.fiore.convextechtestdemo.allResponses.MediaMeatDataObj
import com.holatourism.fiore.convextechtestdemo.databinding.ItemArticlesPicsBinding
import java.util.*


class SelectedArticlePicturesAdapter(
    var mContext: Context,
    private var AllItemList: ArrayList<MediaMeatDataObj>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SelectedArticlePicturesAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DataViewHolder {
        return DataViewHolder(
            ItemArticlesPicsBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup, false
            )
        )
    }


    override fun getItemCount(): Int = AllItemList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(AllItemList[position])
        holder.binding!!.cardDetail.setOnClickListener {
            onItemClickListener.onItemClickListener(position, AllItemList[position])
        }

    }


    class DataViewHolder(_binding: ItemArticlesPicsBinding) :
        RecyclerView.ViewHolder(_binding.root) {

        var binding: ItemArticlesPicsBinding? = null

        init {
            this.binding = _binding
        }

        fun bind(mediaObj: MediaMeatDataObj) {

            if (mediaObj.url != "null") {
                loadImage(mediaObj.url!!, binding!!.imgArticle)
            }
        }
    }

    fun addData(list: List<MediaMeatDataObj>) {
        AllItemList.addAll(list)
    }


    fun removeAt(position: Int) {
        AllItemList.removeAt(position)
        notifyItemRemoved(position)
    }

    interface OnItemClickListener {
        fun onItemClickListener(position: Int, mediaObj: MediaMeatDataObj)
    }


    companion object {
        private fun loadImage(strImgUrl: String, imageView: ImageView) {
            Glide.with(imageView.context)

                .asBitmap()
                .load(strImgUrl)
                .into(object : CustomTarget<Bitmap>() {

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        imageView.setImageBitmap(resource)
                    }
                })
        }
    }
}