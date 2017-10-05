package ra.sumbayak.maverick.core;

import android.content.Context;
import android.support.v4.app.Fragment;

public class MaverickFragment extends Fragment {
    
    protected MaverickActivity context;
    
    @Override
    public void onAttach (Context context) {
        super.onAttach (context);
        this.context = (MaverickActivity) context;
    }
}
