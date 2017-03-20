/*
 * Copyright 2017 Yoko Ahadazaro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ykhdzr.flow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

/**
 * Created by ykhdzr on 3/20/17.
 */

public class Flow {

    private final Object src;

    private final Class dest;

    private final int requestCode;

    private final boolean forResult;

    private final Bundle bundle;

    private final boolean withBundle;

    private final String intentFilter;

    private final boolean withIntentFilter;

    private final int flag;

    private final boolean withFlag;

    private Flow(FlowBuilder flowBuilder) {
        this.src = flowBuilder.src;
        this.dest = flowBuilder.dest;
        this.requestCode = flowBuilder.requestCode;
        this.forResult = flowBuilder.forResult;
        this.bundle = flowBuilder.bundle;
        this.withBundle = flowBuilder.withBundle;
        this.intentFilter = flowBuilder.intentFilter;
        this.withIntentFilter = flowBuilder.withIntentFilter;
        this.flag = flowBuilder.flag;
        this.withFlag = flowBuilder.withFlag;
        start();
    }

    public static FlowBuilder from(Activity activity) {
        return new FlowBuilder(activity);
    }

    public static FlowBuilder from(Fragment fragment) {
        return new FlowBuilder(fragment);
    }

    private void start() {
        if (src instanceof Activity) {
            fromActivity();
        } else {
            fromFragment();
        }
    }

    private void fromActivity() {
        Activity activity = ((Activity) src);
        if (forResult) {
            activity.startActivityForResult(intent(activity), requestCode);
        } else {
            activity.startActivity(intent(activity));
        }
    }

    private void fromFragment() {
        Fragment fragment = ((Fragment) src);
        if (forResult) {
            fragment.startActivityForResult(intent(fragment.getActivity()), requestCode);
        } else {
            fragment.startActivity(intent(fragment.getActivity()));
        }
    }

    private Intent intent(Context context) {
        Intent intent;

        if (withIntentFilter) {
            intent = new Intent(intentFilter);
        } else {
            intent = new Intent(context, dest);
        }

        if (withBundle) {
            intent.putExtras(bundle);
        }

        if (withFlag) {
            intent.setFlags(flag);
        }

        return intent;
    }

    public static class FlowBuilder {

        private Object src;

        private Class dest;

        private int requestCode;

        private boolean forResult;

        private Bundle bundle;

        private boolean withBundle;

        private String intentFilter;

        private boolean withIntentFilter;

        private int flag;

        private boolean withFlag;

        private FlowBuilder(Object src) {
            this.src = src;
        }

        public FlowBuilder with(Bundle bundle) {
            this.withBundle = bundle.size() > 0;
            this.bundle = bundle;
            return this;
        }

        public FlowBuilder forResult(int requestCode) {
            this.forResult = requestCode > 0;
            this.requestCode = requestCode;
            return this;
        }

        public FlowBuilder flag(int flag) {
            withFlag = true;
            this.flag = flag;
            return this;
        }

        public Flow to(Class dest) {
            this.dest = dest;
            return new Flow(this);
        }

        public Flow to(String intentFilter) {
            withIntentFilter = !TextUtils.isEmpty(intentFilter);
            this.intentFilter = intentFilter;
            return new Flow(this);
        }
    }

}
