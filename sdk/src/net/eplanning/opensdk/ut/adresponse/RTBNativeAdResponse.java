/*
 *    Copyright 2017 APPNEXUS INC
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

package net.eplanning.opensdk.ut.adresponse;

import net.eplanning.opensdk.ANAdResponseInfo;
import net.eplanning.opensdk.ANNativeAdResponse;

import java.util.ArrayList;

public class RTBNativeAdResponse extends BaseAdResponse {

    private ANNativeAdResponse nativeAdResponse;

    public RTBNativeAdResponse(int width, int height, String adType, ANNativeAdResponse nativeAdResponse, ArrayList<String> impressionURLs, ANAdResponseInfo adResponseInfo) {
        super(width, height, adType, impressionURLs, adResponseInfo);
        this.nativeAdResponse = nativeAdResponse;
        this.nativeAdResponse.setImpressionType(getImpressionType());
    }

    public ANNativeAdResponse getNativeAdResponse() {
        return nativeAdResponse;
    }
}