package appnexus.example.kotlinsample

import android.app.Application
import android.widget.Toast
import net.eplanning.opensdk.XandrAd

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        XandrAd.init(10094, this, true) {
            Toast.makeText(this, "Init Completed with $it", Toast.LENGTH_SHORT).show()
        }
    }
}