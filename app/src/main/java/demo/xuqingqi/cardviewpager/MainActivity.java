package demo.xuqingqi.cardviewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private CardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mViewPager.setPageTransformer(true, new ScalePageTransformer());
        mAdapter = new CardAdapter(this);
        List<Section> sectionList = createSectionList(20);
        mAdapter.setData(sectionList);
        //在设置数据之后必须得调setAdapter，否则会导致Transform初始状态不正常
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0, true);
    }

    private List<Section> createSectionList(int count) {
        List<Section> sectionList = new ArrayList<>();
        for (int i = 0; i< count; i++) {
            Section section = new Section();
            sectionList.add(section);
        }
        return sectionList;
    }

}
