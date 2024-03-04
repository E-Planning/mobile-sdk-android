/*
 *    Copyright 2018 APPNEXUS INC
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package net.eplanning.opensdk.viewability;

import android.content.Context;

import net.eplanning.opensdk.R;
import net.eplanning.opensdk.SDKSettings;
import net.eplanning.opensdk.utils.Clog;
import net.eplanning.opensdk.utils.Settings;
import net.eplanning.opensdk.utils.StringUtil;
import com.iab.omid.library.microsoft.Omid;
import com.iab.omid.library.microsoft.adsession.Partner;

import java.io.IOException;


public class ANOmidViewabilty {

    private static ANOmidViewabilty omid_instance = null;
    public static final String OMID_PARTNER_NAME = "Microsoft";
    private static String OMID_JS_SERVICE_CONTENT = "";

    private static Partner microsoftPartner = null;

    public static ANOmidViewabilty getInstance() {
        if (omid_instance == null) {
            omid_instance = new ANOmidViewabilty();
            Clog.v(Clog.baseLogTag, Clog.getString(R.string.init));
        }
        return omid_instance;
    }

    private ANOmidViewabilty() {

    }


    public void activateOmidAndCreatePartner(Context applicationContext) {
        // Activate OMID if it is already not
        if (!SDKSettings.getOMEnabled())
            return;

        try {
            if (!Omid.isActive()) {
                Omid.activate(applicationContext);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


        // If OMID active but partner is null then create partner
        if (Omid.isActive() && microsoftPartner == null) {
            try {
                microsoftPartner = Partner.createPartner(OMID_PARTNER_NAME, Settings.getSettings().sdkVersion);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        if (StringUtil.isEmpty(OMID_JS_SERVICE_CONTENT)) {
            try {
                OMID_JS_SERVICE_CONTENT =  StringUtil.getStringFromAsset("apn_omsdk.js", applicationContext);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Partner getMicrosoftPartner() {
        return microsoftPartner;
    }

    public String getOmidJsServiceContent() {
        return OMID_JS_SERVICE_CONTENT;
    }


}
