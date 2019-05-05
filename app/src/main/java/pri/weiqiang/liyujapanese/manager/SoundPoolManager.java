package pri.weiqiang.liyujapanese.manager;

import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import pri.weiqaing.common.base.BaseApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonSound;

public class SoundPoolManager {

    public static final ArrayList<GojuonSound> sounds = new ArrayList<>();
    private static final String TAG = SoundPoolManager.class.getSimpleName();
    private volatile static SoundPoolManager instance = null;

    static {

        sounds.add(new GojuonSound("a", R.raw.a));
        sounds.add(new GojuonSound("ba", R.raw.ba));
        sounds.add(new GojuonSound("be", R.raw.be));
        sounds.add(new GojuonSound("bi", R.raw.bi));
        sounds.add(new GojuonSound("bo", R.raw.bo));
        sounds.add(new GojuonSound("bu", R.raw.bu));
        sounds.add(new GojuonSound("bya", R.raw.bya));
        sounds.add(new GojuonSound("byo", R.raw.byo));
        sounds.add(new GojuonSound("byu", R.raw.byu));
        sounds.add(new GojuonSound("cha", R.raw.cha));
        sounds.add(new GojuonSound("chi", R.raw.chi));
        sounds.add(new GojuonSound("cho", R.raw.cho));
        sounds.add(new GojuonSound("chu", R.raw.chu));
        sounds.add(new GojuonSound("da", R.raw.da));
        sounds.add(new GojuonSound("de", R.raw.de));
        sounds.add(new GojuonSound("do", R.raw.doo));
        sounds.add(new GojuonSound("e", R.raw.e));
        sounds.add(new GojuonSound("fu", R.raw.fu));
        sounds.add(new GojuonSound("ga", R.raw.ga));
        sounds.add(new GojuonSound("ge", R.raw.ge));
        sounds.add(new GojuonSound("gi", R.raw.gi));
        sounds.add(new GojuonSound("go", R.raw.go));
        sounds.add(new GojuonSound("gu", R.raw.gu));
        sounds.add(new GojuonSound("gya", R.raw.gya));
        sounds.add(new GojuonSound("gyo", R.raw.gyo));
        sounds.add(new GojuonSound("gyu", R.raw.gyu));
        sounds.add(new GojuonSound("ha", R.raw.ha));
        sounds.add(new GojuonSound("he", R.raw.he));
        sounds.add(new GojuonSound("hi", R.raw.hi));
        sounds.add(new GojuonSound("ho", R.raw.ho));
        sounds.add(new GojuonSound("hya", R.raw.hya));
        sounds.add(new GojuonSound("hyo", R.raw.hyo));
        sounds.add(new GojuonSound("hyu", R.raw.hyu));
        sounds.add(new GojuonSound("i", R.raw.i));
        sounds.add(new GojuonSound("ja", R.raw.ja));
        sounds.add(new GojuonSound("ji", R.raw.ji));
        sounds.add(new GojuonSound("jo", R.raw.jo));
        sounds.add(new GojuonSound("ju", R.raw.ju));
        sounds.add(new GojuonSound("ka", R.raw.ka));
        sounds.add(new GojuonSound("ke", R.raw.ke));
        sounds.add(new GojuonSound("ki", R.raw.ki));
        sounds.add(new GojuonSound("ko", R.raw.ko));
        sounds.add(new GojuonSound("ku", R.raw.ku));
        sounds.add(new GojuonSound("kya", R.raw.kya));
        sounds.add(new GojuonSound("kyo", R.raw.kyo));
        sounds.add(new GojuonSound("kyu", R.raw.kyu));
        sounds.add(new GojuonSound("ma", R.raw.ma));
        sounds.add(new GojuonSound("me", R.raw.me));
        sounds.add(new GojuonSound("mi", R.raw.mi));
        sounds.add(new GojuonSound("mo", R.raw.mo));
        sounds.add(new GojuonSound("mu", R.raw.mu));
        sounds.add(new GojuonSound("mya", R.raw.mya));
        sounds.add(new GojuonSound("myo", R.raw.myo));
        sounds.add(new GojuonSound("myu", R.raw.myu));
        sounds.add(new GojuonSound("n", R.raw.n));
        sounds.add(new GojuonSound("na", R.raw.na));
        sounds.add(new GojuonSound("ne", R.raw.ne));
        sounds.add(new GojuonSound("ni", R.raw.ni));
        sounds.add(new GojuonSound("no", R.raw.no));
        sounds.add(new GojuonSound("nu", R.raw.nu));
        sounds.add(new GojuonSound("nya", R.raw.nya));
        sounds.add(new GojuonSound("nyo", R.raw.nyo));
        sounds.add(new GojuonSound("nyu", R.raw.nyu));
        sounds.add(new GojuonSound("o", R.raw.o));
        sounds.add(new GojuonSound("pa", R.raw.pa));
        sounds.add(new GojuonSound("pe", R.raw.pe));
        sounds.add(new GojuonSound("pi", R.raw.pi));
        sounds.add(new GojuonSound("po", R.raw.po));
        sounds.add(new GojuonSound("pu", R.raw.pu));
        sounds.add(new GojuonSound("pya", R.raw.pya));
        sounds.add(new GojuonSound("pyo", R.raw.pyo));
        sounds.add(new GojuonSound("pyu", R.raw.pyu));
        sounds.add(new GojuonSound("ra", R.raw.ra));
        sounds.add(new GojuonSound("re", R.raw.re));
        sounds.add(new GojuonSound("ri", R.raw.ri));
        sounds.add(new GojuonSound("ro", R.raw.ro));
        sounds.add(new GojuonSound("ru", R.raw.ru));
        sounds.add(new GojuonSound("rya", R.raw.rya));
        sounds.add(new GojuonSound("ryo", R.raw.ryo));
        sounds.add(new GojuonSound("ryu", R.raw.ryu));
        sounds.add(new GojuonSound("sa", R.raw.sa));
        sounds.add(new GojuonSound("se", R.raw.se));
        sounds.add(new GojuonSound("sha", R.raw.sha));
        sounds.add(new GojuonSound("shi", R.raw.shi));
        sounds.add(new GojuonSound("sho", R.raw.sho));
        sounds.add(new GojuonSound("shu", R.raw.shu));
        sounds.add(new GojuonSound("so", R.raw.so));
        sounds.add(new GojuonSound("su", R.raw.su));
        sounds.add(new GojuonSound("ta", R.raw.ta));
        sounds.add(new GojuonSound("te", R.raw.te));
        sounds.add(new GojuonSound("to", R.raw.to));
        sounds.add(new GojuonSound("tsu", R.raw.tsu));
        sounds.add(new GojuonSound("u", R.raw.u));
        sounds.add(new GojuonSound("wa", R.raw.wa));
        sounds.add(new GojuonSound("wo", R.raw.o));
        sounds.add(new GojuonSound("ya", R.raw.ya));
        sounds.add(new GojuonSound("yo", R.raw.yo));
        sounds.add(new GojuonSound("yu", R.raw.yu));
        sounds.add(new GojuonSound("za", R.raw.za));
        sounds.add(new GojuonSound("ze", R.raw.ze));
        sounds.add(new GojuonSound("zo", R.raw.zo));
        sounds.add(new GojuonSound("zu", R.raw.zu));
        sounds.add(new GojuonSound("zi", R.raw.zi));
        sounds.add(new GojuonSound("wo", R.raw.wo));
        sounds.add(new GojuonSound("du", R.raw.du));


    }

    SoundPool soundPool = null;
    Map<String, Integer> soundsIdMap = new ConcurrentHashMap<>();

    private SoundPoolManager() {

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

    }

    public static SoundPoolManager getInstance() {
        if (instance == null) {
            synchronized (SoundPoolManager.class) {
                if (instance == null) {
                    instance = new SoundPoolManager();
                }
            }
        }
        return instance;
    }

    public void init() {
        //使用线程池在开始界面缓存全部五十音图
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5,
                1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100));
        for (GojuonSound sound : sounds) {
            Runnable runnable = () -> {
                int id = soundPool.load(BaseApplication.getInstance(), sound.getResId(), 1);
                soundsIdMap.put(sound.getRome(), id);
            };
            threadPoolExecutor.execute(runnable);
        }
        Log.e(TAG, "ThreadPoolExecutor GojuonSound init finish.");


    }

    public void play(String rome) {

        if (soundsIdMap.get(rome) != null) {
            soundPool.play(soundsIdMap.get(rome), 1, 1, 0, 0, 1);
        } else {
            int i = 0;
            for (; i < sounds.size(); i++) {
                if (sounds.get(i).getRome().equals(rome))
                    break;
            }
            final int soundID = soundPool.load(BaseApplication.getInstance(), sounds.get(i).getResId(), 1);
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    soundPool.play(soundID, 1, 1, 0, 0, 1);
                }
            });
        }

    }


}
