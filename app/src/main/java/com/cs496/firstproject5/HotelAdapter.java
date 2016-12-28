package com.cs496.firstproject5;

/**
 * Created by q on 2016-12-27.
 */


        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.widget.PopupMenu;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;

        import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyViewHolder> {

    private Context mContext;
    private List<Hotel> hotelList;
    private List<Hotel> starredList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public HotelAdapter(Context mContext, List<Hotel> hotelList, List<Hotel> starredList) {
        this.mContext = mContext;
        this.hotelList = hotelList;
        this.starredList = starredList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Hotel hotel = hotelList.get(position);
        holder.title.setText(hotel.getName());
        holder.count.setText("$"+hotel.getPrice());

        // loading album cover using Glide library
        Glide.with(mContext).load(hotel.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow,hotel);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view,Hotel hotel) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_hotel, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(hotel));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        private Hotel h;
        public MyMenuItemClickListener(Hotel hotel) {
            h = hotel;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_star:
                    if (starredList.contains(h)) {
                        Toast.makeText(mContext, "Unstarred! ☆", Toast.LENGTH_SHORT).show();
                        starredList.remove(h);
                    } else {
                        Toast.makeText(mContext, "Starred! ★", Toast.LENGTH_SHORT).show();
                        starredList.add(h);
                    }
                    return true;
                case R.id.action_detail:
                    Intent intent = new Intent(mContext, PopDetails.class);
                    intent.putExtra("name", h.getName());
                    intent.putExtra("price", h.getPrice());
                    intent.putExtra("tocity", h.getToCity());
                    intent.putExtra("toeiffel", h.getToEiffel());
                    intent.putExtra("rating", h.getRating());
                    intent.putExtra("star", h.getStar());
                    mContext.startActivity(intent);
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}
