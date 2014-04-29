package fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.wljr.wanglibao.R;

import dao.Fund;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * 精品推荐界面
 * @author Administrator
 *
 */
public class Recommend extends Fragment {
	private ImageView[] imageViews = null;
	private ImageView imageView = null;
	private ViewPager advPager = null;
	private AtomicInteger what = new AtomicInteger(0);
	private boolean isContinue = true;
	private RelativeLayout raLayout;
    private ListView listView;
    private List<Fund> funds = new ArrayList<Fund>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// TODO Auto-generated method stub
		 View view = inflater.inflate(R.layout.activity_recommend, null);
		 raLayout=(RelativeLayout)view.findViewById(R.id.relayout);//广告位布局
		 listView=(ListView)view.findViewById(R.id.listView1);//下方列表
		 advPager = (ViewPager) view.findViewById(R.id.adv_pager);
			// 图片列表
			List<View> advPics = new ArrayList<View>();
			// 图片1
			ImageView img1 = new ImageView(getActivity());
			img1.setBackgroundResource(R.drawable.advertising_default_1);
			advPics.add(img1);
			// 图片2
			ImageView img2 = new ImageView(getActivity());
			img2.setBackgroundResource(R.drawable.advertising_default_2);
			advPics.add(img2);
			// 图片3
			ImageView img3 = new ImageView(getActivity());
			img3.setBackgroundResource(R.drawable.advertising_default_3);
			advPics.add(img3);
			// 图片4
			ImageView img4 = new ImageView(getActivity());
			img4.setBackgroundResource(R.drawable.advertising_default);
			advPics.add(img4);
	  
			// group是R.layou.mainview中的负责包裹小圆点的LinearLayout.
			ViewGroup group = (ViewGroup)view.findViewById(R.id.viewGroup);
			imageViews = new ImageView[advPics.size()];

			for (int i = 0; i < advPics.size(); i++) {
				imageView = new ImageView(getActivity());
				imageView.setLayoutParams(new LayoutParams(20, 20));
				imageView.setPadding(30, 0, 30, 0);
				imageViews[i] = imageView;
				if (i == 0) {
					// 默认选中第一张图片
					imageViews[i]
							.setBackgroundResource(R.drawable.red);
				} else {
					imageViews[i]
							.setBackgroundResource(R.drawable.white);
				}
				group.addView(imageViews[i]);
			}

			advPager.setAdapter(new AdvAdapter(advPics));
			advPager.setOnPageChangeListener(new GuidePageChangeListener());
			//按下时不继续定时滑动,弹起时继续定时滑动
			advPager.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_MOVE:
						isContinue = false;
						break;
					case MotionEvent.ACTION_UP:
						isContinue = true;
						break;
					default:
						isContinue = true;
						break;
					}
					return false;
				}
			});
			// 定时滑动线程
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						if (isContinue) {
							viewHandler.sendEmptyMessage(what.get());
							whatOption();
						}
					}
				}

			}).start();
		return view;
	}
	
	
	

	/**
	 * 操作圆点轮换变背景
	 */
	private void whatOption() {
		what.incrementAndGet();
		if (what.get() > imageViews.length - 1) {
			what.getAndAdd(-4);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
	}

	/**
	 * 处理定时切换广告栏图片的句柄
	 */
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			advPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}

	};

	/** 指引页面改监听器 */
	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0]
						.setBackgroundResource(R.drawable.red);
				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.white);
				}
			}

		}

	}

	/**
	 * 广告栏PaperView 图片适配器
	 * 
	 * @author wx
	 * 
	 */
	private final class AdvAdapter extends PagerAdapter {
		private List<View> views = null;

		public AdvAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {

		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(views.get(arg1), 0);
			return views.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

	}
	
	class Myadapter extends BaseAdapter{
		
		public Myadapter(){
    	 
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	 
	
}
