package com.getpeerkat.peerkat.onboard;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.getpeerkat.peerkat.R;
import com.viewpagerindicator.CirclePageIndicator;


/**
 * Created by dvorkapic on 17/09/15.
 */
public class ActivityTutorial extends AppCompatActivity {

    private static final int NUM_PAGES = 4;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private Button button;
    private CirclePageIndicator circlePageIndicator;
    private Fragment fragmentOne;
    private Fragment fragmentTwo;
    private Fragment fragmentThree;
    private Fragment fragmentFour;



    public ActivityTutorial() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutorial);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(4);

        circlePageIndicator = (CirclePageIndicator)findViewById(R.id.viewpager_indicator);
        circlePageIndicator.setViewPager(viewPager);
        circlePageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    button.setText(R.string.gotit);
                } else {
                    button.setText(R.string.next);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextSlide();
            }
        });

        fragmentOne =  new TutorialFragmentOne();
        fragmentTwo =  new TutorialFragmentTwo();
        fragmentThree =  new TutorialFragmentThree();
        fragmentFour =  new TutorialFragmentFour();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
//            animatePagerTransition(false);
            viewPager.setCurrentItem(viewPager.getCurrentItem()-1, true);
        }
    }

    public synchronized void nextSlide() {
        if (viewPager.getCurrentItem() < 3) {
//            animatePagerTransition(true); //This for some reason makes a page skip ahead one step?
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1, true);
        } else {
            Intent i = new Intent(this, ActivityRegister.class);
            startActivity(i);
            finish();
        }
    }

    private void animatePagerTransition(final boolean forward) {

        ValueAnimator animator = ValueAnimator.ofInt(0, viewPager.getWidth());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                viewPager.endFakeDrag();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                viewPager.endFakeDrag();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            private int oldDragPosition = 0;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dragPosition = (Integer) animation.getAnimatedValue();
                int dragOffset = dragPosition - oldDragPosition;
                oldDragPosition = dragPosition;
                viewPager.fakeDragBy(dragOffset * (forward ? -1 : 1));
            }
        });

        animator.setDuration(800);
        viewPager.beginFakeDrag();
        animator.start();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragmentOne;
                case 1:
                    return fragmentTwo;
                case 2:
                    return fragmentThree;
                case 3:
                    return fragmentFour;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public static class TutorialFragmentOne extends Fragment {

        public TutorialFragmentOne() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.tutorial_fragment_one, container, false);
            return rootView;
        }
    }

    public static class TutorialFragmentTwo extends Fragment {
        public TutorialFragmentTwo() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.tutorial_fragment_two, container, false);
            return rootView;
        }
    }

    public static class TutorialFragmentThree extends Fragment {
        public TutorialFragmentThree() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.tutorial_fragment_three, container, false);
            return rootView;
        }
    }

    public static class TutorialFragmentFour extends Fragment {
        public TutorialFragmentFour() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.tutorial_fragment_four, container, false);
            return rootView;
        }
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.2f;
        private static final float MIN_ALPHA = 0.2f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
