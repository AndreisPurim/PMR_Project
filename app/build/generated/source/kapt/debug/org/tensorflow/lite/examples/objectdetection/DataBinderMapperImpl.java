package org.tensorflow.lite.examples.objectdetection;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.tensorflow.lite.examples.objectdetection.databinding.ActivityHomePageBindingImpl;
import org.tensorflow.lite.examples.objectdetection.databinding.ActivitySeeBindingImpl;
import org.tensorflow.lite.examples.objectdetection.databinding.ActivitySpeeckToText2BindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYHOMEPAGE = 1;

  private static final int LAYOUT_ACTIVITYSEE = 2;

  private static final int LAYOUT_ACTIVITYSPEECKTOTEXT2 = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.tensorflow.lite.examples.objectdetection.R.layout.activity_home_page, LAYOUT_ACTIVITYHOMEPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.tensorflow.lite.examples.objectdetection.R.layout.activity_see, LAYOUT_ACTIVITYSEE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.tensorflow.lite.examples.objectdetection.R.layout.activity_speeck_to_text2, LAYOUT_ACTIVITYSPEECKTOTEXT2);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYHOMEPAGE: {
          if ("layout/activity_home_page_0".equals(tag)) {
            return new ActivityHomePageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_home_page is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSEE: {
          if ("layout/activity_see_0".equals(tag)) {
            return new ActivitySeeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_see is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSPEECKTOTEXT2: {
          if ("layout/activity_speeck_to_text2_0".equals(tag)) {
            return new ActivitySpeeckToText2BindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_speeck_to_text2 is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/activity_home_page_0", org.tensorflow.lite.examples.objectdetection.R.layout.activity_home_page);
      sKeys.put("layout/activity_see_0", org.tensorflow.lite.examples.objectdetection.R.layout.activity_see);
      sKeys.put("layout/activity_speeck_to_text2_0", org.tensorflow.lite.examples.objectdetection.R.layout.activity_speeck_to_text2);
    }
  }
}
