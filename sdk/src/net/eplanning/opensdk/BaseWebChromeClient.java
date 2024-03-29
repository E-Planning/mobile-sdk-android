/*
 *    Copyright 2013 APPNEXUS INC
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

package net.eplanning.opensdk;

import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import net.eplanning.opensdk.utils.Clog;

class BaseWebChromeClient extends WebChromeClient {

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Clog.v(Clog.jsLogTag,
                Clog.getString(net.eplanning.opensdk.R.string.console_message,
                        consoleMessage.message(),
                        consoleMessage.lineNumber(),
                        consoleMessage.sourceId()));
        return true;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message,
                             JsResult result) {
        Clog.v(Clog.jsLogTag,
                Clog.getString(net.eplanning.opensdk.R.string.js_alert, message, url));
        result.confirm();
        return true;
    }
}
