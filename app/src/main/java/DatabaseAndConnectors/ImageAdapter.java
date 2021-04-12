package DatabaseAndConnectors;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.listings.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;
    private OnItemClickListner mListner;

    //Constructor to get values from outside
    public ImageAdapter(Context context, List<Upload> uploads)
    {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_grid_layout, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textViewEmail.setText(uploadCurrent.getSellingInformationEmail());
        holder.textViewDescription.setText(uploadCurrent.getImageDescription());
        holder.textViewLocation.setText(uploadCurrent.getImageLocation());
        Glide.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .apply(new RequestOptions().override(500,500))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView textViewName;
        public TextView textViewEmail;
        public TextView textViewDescription;
        public ImageView imageView;
        public TextView textViewLocation;

        public ImageViewHolder(View itemView)
        {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_image_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
            textViewEmail = itemView.findViewById(R.id.text_image_email);
            textViewDescription = itemView.findViewById(R.id.text_image_description);
            textViewLocation = itemView.findViewById(R.id.text_image_location);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListner != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                {
                    mListner.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select Action");
            MenuItem delete = contextMenu.add(Menu.NONE,1,1, "Delete");

            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (mListner != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                {
                    switch (menuItem.getItemId()){
                        case 1:
                            mListner.onDeleteClick(position);
                            return true;
                    }

                }
            }
            return false;
        }
    }

    public interface OnItemClickListner{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClickListner(OnItemClickListner listner)
    {
        mListner = listner;
    }
}

