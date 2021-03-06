package sis.pewpew.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import sis.pewpew.R;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder> {

    private Context context;

    public EventsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    private String[] titles = {"Экофестиваль 1",
            "Экофестиваль 2",
            "Экофестиваль 3",
    };

    private String[] details = {"Карточка события \"Экофестиваль 1\" в демоверсии создана исключительно для демонстрационных целей и не содержит актуальной информации о каком-либо событии.",
            "Карточка события \"Экофестиваль 2\" в демоверсии создана исключительно для демонстрационных целей и не содержит актуальной информации о каком-либо событии.",
            "Карточка события \"Экофестиваль 3\" в демоверсии создана исключительно для демонстрационных целей и не содержит актуальной информации о каком-либо событии.",
    };

    private int[] images = {R.drawable.fest_icon,
            R.drawable.fest_icon_2,
            R.drawable.fest_icon_3,
    };


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;
        TextView itemDetail;
        Button shareButton;

        ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.item_image);
            itemTitle = (TextView) itemView.findViewById(R.id.item_title);
            itemDetail = (TextView) itemView.findViewById(R.id.item_detail);
            shareButton = (Button) itemView.findViewById(R.id.event_share_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final android.app.AlertDialog.Builder eventCardDialog = new android.app.AlertDialog.Builder(context);
                    eventCardDialog.setTitle("Карточка события");
                    eventCardDialog.setMessage("Чтобы просмотреть более детальную информацию об этом событии, " +
                            "коснитесь его информационного окна на карте.");
                    eventCardDialog.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    eventCardDialog.show();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.events_card_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);
        viewHolder.itemImage.setImageResource(images[i]);
        final String title = titles[i];
        viewHolder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "Событие: " + title;
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(shareIntent, "Поделиться событием"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}