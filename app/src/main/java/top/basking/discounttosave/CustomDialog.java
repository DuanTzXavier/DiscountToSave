package top.basking.discounttosave;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by tzduan on 2018/7/19.
 */

public class CustomDialog extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStyle(2, R.style.ThemeHolo);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FrameLayout layout = new FrameLayout(this.getActivity());
        View v= LayoutInflater.from(getActivity()).inflate(R.layout.discount, container);
        layout.addView(v);
        CardView cardView = v.findViewById(R.id.card_view);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.green));
        return layout;
    }
}
