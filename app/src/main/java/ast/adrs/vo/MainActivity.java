package ast.adrs.vo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.armoomragames.denketa.R;
import com.google.android.material.navigation.NavigationView;

import ast.adrs.vo.IntroAuxilaries.WebServices.AppConfig;
import ast.adrs.vo.IntroAuxilaries.WebServices.AppConstt;
import ast.adrs.vo.MainAuxilaries.HomeFragment;
import ast.adrs.vo.MainAuxilaries.ImmediateDiseasesReportsIDRFragment;
import ast.adrs.vo.MainAuxilaries.PerformanceMonitoringFragment;
import ast.adrs.vo.Utils.IBadgeUpdateListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IBadgeUpdateListener {

    LinearLayout llPriority_Sector, llDashboard, llPerformancemonitoring, llRapid, llissues, llFeedback, llImmediateDiseasesReportsIDR, llIssuesFacedPrivate, llLogout;

    FragmentTransaction ft;
    RelativeLayout rlBotmbar;
    public DrawerLayout drawer;
    NavigationView navigationView;
    private FragmentManager fm;

    TextView txvTitleBar;
    RelativeLayout rlToolbar, rlMenu ,idr;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        AppConfig.getInstance().performLangCheck(getWindow());
        if (savedInstanceState != null) {
            return;
        }


            init();
            bindviews();
            navToHomeFragment();

        }





    private void bindviews() {
        drawer = findViewById(R.id.act_main_drawr);
        navigationView = drawer.findViewById(R.id.act_main_navview);
        rlBotmbar = findViewById(R.id.act_main_rl_bttabbar);


        txvTitleBar = findViewById(R.id.act_intro_txv_title);
        rlMenu = findViewById(R.id.act_intro_rl_toolbar_menu);
        idr = findViewById(R.id.homebar);

        llPriority_Sector = findViewById(R.id.lay_navigationview_llPriority_Sector);
        llPerformancemonitoring = findViewById(R.id.lay_navigationview_llPerformancemonitoring);
        llDashboard = findViewById(R.id.lay_navigationview_llDashboard);
        llRapid = findViewById(R.id.lay_navigationview_llRapid);

        llFeedback = findViewById(R.id.lay_navigationview_llFeedback);
        llImmediateDiseasesReportsIDR = findViewById(R.id.lay_navigationview_llimmediatediseasesreports_idr);
        llIssuesFacedPrivate = findViewById(R.id.lay_navigationview_llIssuesFacedPrivate);
        llLogout = findViewById(R.id.lay_navigationview_llLogout);
        llissues = findViewById(R.id.lay_navigationview_llissues);

        rlMenu.setOnClickListener(this);
        idr.setOnClickListener(this);
//
//        llPriority_Sector.setOnClickListener(this);
        llPerformancemonitoring.setOnClickListener(this);
        llDashboard.setOnClickListener(this);
//        llRapid.setOnClickListener(this);
//        llissues.setOnClickListener(this);
//        llFeedback.setOnClickListener(this);

        //use For Example
        llImmediateDiseasesReportsIDR.setOnClickListener(this);

//        llIssuesFacedPrivate.setOnClickListener(this);
        llLogout.setOnClickListener(this);


        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // Whatever you want
            //   changeDrawerItemState();
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                // Whatever you want
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                // Whatever you want
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // Whatever you want
            }
        });



    }
    public void navToHomeFragment() {
        clearMyBackStack();
        Fragment frg = new HomeFragment();
        // TODO: 09-Nov-21 change frg here
      //  Fragment frg = new ExampleFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_HomeFragment);
        ft.commit();

    }


    private void init() {
        fm = getSupportFragmentManager();
    }


    public void navToImmediateDiseasesReportsIDR() {
        Fragment frg = new ImmediateDiseasesReportsIDRFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_ImmediateDiseasesReportsIDR);
        ft.addToBackStack(AppConstt.FragTag.FN_ImmediateDiseasesReportsIDR);
        hideLastStackFragment(ft);
        ft.commit();
    }

    public void navToPerformanceMonitoringFragment() {
        Fragment frg = new PerformanceMonitoringFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_PerformanceMonitoringFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_PerformanceMonitoringFragment);
        hideLastStackFragment(ft);
        ft.commit();
    }

    public void navToPrimeMinisterPrioritySectorFragment() {
//        Fragment frg = new PrimeMinisterPrioritySectorFragment();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_PrimeMinisterFragment);
//        ft.addToBackStack(AppConstt.FragTag.FN_PrimeMinisterFragment);
//        hideLastStackFragment(ft);
//        ft.commit();
    }

    public void navToIssuesFaced() {
//        Fragment frg = new IssuesFacedByPrivateSectorFragment();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_IssuesFacedFragment);
//
//        ft.addToBackStack(AppConstt.FragTag.FN_IssuesFacedFragment);
//        hideLastStackFragment(ft);
//        ft.commit();
    }

    public void navToRapidAssessment() {
//        Fragment frg = new RapidAssessmentFragment();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_RapidAssessment);
//        ft.addToBackStack(AppConstt.FragTag.FN_RapidAssessment);
//        hideLastStackFragment(ft);
//        ft.commit();
    }

    public void navToissues() {
//        Fragment frg = new FragmentIssues();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_issues);
//        ft.addToBackStack(AppConstt.FragTag.FN_issues);
//        hideLastStackFragment(ft);
//        ft.commit();
    }


    public void navToFeedbackReport() {
//        Fragment frg = new FeedbackReportFragment();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_FeedbackReport);
//        ft.addToBackStack(AppConstt.FragTag.FN_FeedbackReport);
//        hideLastStackFragment(ft);
//        ft.commit();
    }

    public void clearMyBackStack() {
        int count = fm.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fm.popBackStackImmediate();

        }

    }


    public void hideLastStackFragment(FragmentTransaction ft) {
//        Fragment frg = null;
//        frg = getSupportFragmentManager().findFragmentById(R.id.act_main_content_frg);
//
//        if (frg != null) {
//            if (frg instanceof HomeFragment && frg.isVisible()) {
//                ft.hide(frg);
//            }
//            if (frg instanceof PunjabBusinessRegulatoryFragment && frg.isVisible()) {
//                ft.hide(frg);
//            } else if (frg instanceof RegulatoryMappingFragment && frg.isVisible()) {
//                ft.hide(frg);
//            } else if (frg instanceof PrimeMinisterPrioritySectorFragment && frg.isVisible()) {
//                ft.hide(frg);
//            } else if (frg instanceof IssuesFacedByPrivateSectorFragment && frg.isVisible()) {
//                ft.hide(frg);
//            } else if (frg instanceof RapidAssessmentFragment && frg.isVisible()) {
//                ft.hide(frg);
//            } else if (frg instanceof FeedbackReportFragment && frg.isVisible()) {
//                ft.hide(frg);
//            } else if (frg instanceof FragmentIssues && frg.isVisible()) {
//                ft.hide(frg);
//            }
//        }


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_intro_rl_toolbar_menu:
                openDrawar();
                break;
            case R.id.lay_navigationview_llimmediatediseasesreports_idr:
                closeDrawar();
                navToImmediateDiseasesReportsIDR();
                break;
            case R.id.lay_navigationview_llDashboard:
                closeDrawar();
               // navToHomeFragment();
                break;

            case R.id.lay_navigationview_llPerformancemonitoring:
                closeDrawar();
                navToPerformanceMonitoringFragment();
                break;

            case R.id.lay_navigationview_llPriority_Sector:
                closeDrawar();
                navToPrimeMinisterPrioritySectorFragment();
                break;

            case R.id.lay_navigationview_llIssuesFacedPrivate:
                closeDrawar();
                navToIssuesFaced();
                break;

            case R.id.lay_navigationview_llRapid:
                closeDrawar();
                navToRapidAssessment();
                break;

            case R.id.lay_navigationview_llissues:
                closeDrawar();
                navToissues();
                break;

            case R.id.lay_navigationview_llFeedback:
                closeDrawar();
                navToFeedbackReport();
                break;


            case R.id.lay_navigationview_llLogout:
                AppConfig.getInstance().navtoLogin();
                break;
        }


    }

    //region Ibadge
    @Override
    public void setBottomTabVisiblity(int mVisibility) {

    }
    @Override
    public void setToolbarVisiblity(int mVisibility) {
//        rlToolbar.setVisibility(mVisibility);
    }

    @Override
    public void setToolbarState(byte mState) {
        switch (mState) {
            case AppConstt.ToolbarState.TOOLBAR_BACK_HIDDEN:
                closeDrawar();

                rlMenu.setVisibility(View.VISIBLE);
                txvTitleBar.setVisibility(View.VISIBLE);
                idr.setVisibility(View.VISIBLE);
                break;

//            case AppConstt.ToolbarState.TOOLBAR_VISIBLE:
//                closeDrawar();
//
//                rlMenu.setVisibility(View.VISIBLE);
//                txvTitleBar.setVisibility(View.VISIBLE);
//                break;
        }
    }

    @Override
    public void switchStatusBarColor(boolean isDark) {

    }

    @Override
    public boolean setHeaderTitle(String strAppTitle) {
        try {
            txvTitleBar.setText(strAppTitle + "");
        }
        catch (Exception e)
        {

        }

        return false;
    }
    //endregion

    //region Drawer
    public void openDrawar() {

        drawer.openDrawer(GravityCompat.START);

    }

    public void closeDrawar() {

        drawer.closeDrawer(GravityCompat.START);
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void lockDrawar() {

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void unlockDrawar() {

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
    //endregion

    @Override
    public void onBackPressed() {

        AppConfig.getInstance().closeKeyboard(this);

        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStack();

            } else {

                super.onBackPressed();
            }
        }

    }}