package de.naturalsoft.bakingapp.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Steps;
import de.naturalsoft.bakingapp.ui.shared.ViewModelFactory;
import de.naturalsoft.bakingapp.ui.shared.fragment.BaseFragment;
import de.naturalsoft.bakingapp.utils.BakingHelper;
import de.naturalsoft.bakingapp.utils.Injector;

/**
 * BackingApp
 * Created by Thomas Schmidt on 14.07.2018.
 */
public class DetailVideoFragment extends BaseFragment implements ViewModelInterface {

    private final static String CLASSTAG = DetailFragment.class.getSimpleName();

    private static Fragment mFragment;

    private static long lastPosition = 0;

    private TextView descriptionTextView;
    private PlayerView playerView;

    private DetailViewModel detailViewModel;
    private SimpleExoPlayer mExoPlayer;

    private Observer<Steps> observer = steps -> {

        if (steps != null) {
            releasePlayer();

            String tempUrl = steps.getVideoURL();
            lateBindingViews();


            String description;
            boolean is_landscapeMode = BakingHelper.isLandscapeMode(getContext());

            if (!tempUrl.isEmpty()) {

                if (!is_landscapeMode) {
                    descriptionTextView.setText(steps.getDescription());
                } else {
                    descriptionTextView.setVisibility(View.GONE);
                }

                initialPlayerSetup();
                playerView.setVisibility(View.VISIBLE);
                playerView.setPlayer(mExoPlayer);

                mExoPlayer.prepare(buildMediaSource(Uri.parse(tempUrl)));
                mExoPlayer.seekTo(lastPosition);
                mExoPlayer.setPlayWhenReady(true);

            } else {
                description = buildDescription(steps);

                playerView.setVisibility(View.GONE);
                descriptionTextView.setVisibility(View.VISIBLE);
                descriptionTextView.setText(description);
            }

        } else {
            Log.d(CLASSTAG, getString(R.string.no_steps_provided));
        }
    };

    /**
     * Build description for
     * Step without video
     *
     * @param steps Current Step
     * @return builded String
     */
    private String buildDescription(Steps steps) {
        StringBuilder builder = new StringBuilder();
        builder.append(getString(R.string.no_video_provided));
        builder.append("\n");
        builder.append(steps.getDescription());
        return builder.toString();
    }

    public static DetailVideoFragment getInstance() {
        if (mFragment == null) {
            mFragment = new DetailVideoFragment();
        }
        return (DetailVideoFragment) mFragment;
    }

    @Override
    protected int getFragmentLayoutResourceId() {
        return R.layout.detail_video_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelFactory factory = (ViewModelFactory) Injector.provideViewModelFactory(getContext());
        detailViewModel = ViewModelProviders.of(getActivity(), factory).get(DetailViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        detailViewModel.getStep().observe(this, observer);
    }

    private MediaSource buildMediaSource(Uri uri) {
        String userAgent = Util.getUserAgent(getActivity(), getString(R.string.app_name));
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(getActivity(), userAgent);

        return new ExtractorMediaSource(uri, defaultDataSourceFactory, new DefaultExtractorsFactory(), null, null);
    }

    private void initialPlayerSetup() {

        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();

        mExoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getActivity()),
                trackSelector, loadControl);
    }

    private void lateBindingViews() {
        playerView = getView().findViewById(R.id.player_view);
        descriptionTextView = getView().findViewById(R.id.recipe_description);
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer != null) mExoPlayer.stop();
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) lastPosition = savedInstanceState.getLong("position");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mExoPlayer != null) outState.putLong("position", mExoPlayer.getCurrentPosition());
    }

    @Override
    public DetailViewModel getViewModel() {
        return detailViewModel;
    }
}
