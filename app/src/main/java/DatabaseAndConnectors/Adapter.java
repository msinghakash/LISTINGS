/*

package DatabaseAndConnectors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listings.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> titles;
    List<Integer> images;
    //Layout inflater will bind the custom layout resource file to our recycler view so that it can be used.
    LayoutInflater inflater;

    public Adapter(Context ctx, List<String> titles, List<Integer> images)
    {
        //Assigning data passed from the main activity
        this.titles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(ctx);//Layout Inflator called from the context passed from the user.

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageIconText.setText(titles.get(position));
        holder.imageIcon.setImageResource(images.get(position));


    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView imageIconText;
        ImageView imageIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageIconText = itemView.findViewById(R.id.imageIconText);
            imageIcon = itemView.findViewById(R.id.imageIcon);
        }
    }
}


*/
