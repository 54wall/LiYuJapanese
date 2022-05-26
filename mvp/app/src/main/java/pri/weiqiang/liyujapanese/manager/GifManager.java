package pri.weiqiang.liyujapanese.manager;

import android.util.Log;

import java.util.concurrent.ConcurrentHashMap;

import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonGif;

public class GifManager {

    private static final String TAG = GifManager.class.getSimpleName();
    private volatile static GifManager instance;

    public ConcurrentHashMap<String, GojuonGif> gifs = new ConcurrentHashMap<>();

    private GifManager() {
    }

    public static GifManager getInstance() {
        if (instance == null) {
            synchronized (GifManager.class) {
                if (instance == null) {
                    instance = new GifManager();
                }
            }
        }
        return instance;
    }

    public void init() {
        gifs.put("a", new GojuonGif("a", R.raw.gif_a, R.raw.gif_a_));
        gifs.put("ba", new GojuonGif("ba", R.raw.gif_ba, R.raw.gif_ba_));
        gifs.put("be", new GojuonGif("be", R.raw.gif_be, R.raw.gif_be_));
        gifs.put("bi", new GojuonGif("bi", R.raw.gif_bi, R.raw.gif_bi_));
        gifs.put("bo", new GojuonGif("bo", R.raw.gif_bo, R.raw.gif_bo_));
        gifs.put("bu", new GojuonGif("bu", R.raw.gif_bu, R.raw.gif_bu_));
        gifs.put("chi", new GojuonGif("chi", R.raw.gif_chi, R.raw.gif_chi_));
        gifs.put("da", new GojuonGif("da", R.raw.gif_da, R.raw.gif_da_));
        gifs.put("de", new GojuonGif("de", R.raw.gif_de, R.raw.gif_de_));
        gifs.put("do", new GojuonGif("do", R.raw.gif_do, R.raw.gif_do_));
        gifs.put("du", new GojuonGif("du", R.raw.gif_du, R.raw.gif_du_));
        gifs.put("e", new GojuonGif("e", R.raw.gif_e, R.raw.gif_e_));
        gifs.put("fu", new GojuonGif("fu", R.raw.gif_fu, R.raw.gif_fu_));
        gifs.put("ga", new GojuonGif("ga", R.raw.gif_ga, R.raw.gif_ga_));
        gifs.put("ge", new GojuonGif("ge", R.raw.gif_ge, R.raw.gif_ge_));
        gifs.put("gi", new GojuonGif("gi", R.raw.gif_gi, R.raw.gif_gi_));
        gifs.put("go", new GojuonGif("go", R.raw.gif_go, R.raw.gif_go_));
        gifs.put("gu", new GojuonGif("gu", R.raw.gif_gu, R.raw.gif_gu_));
        gifs.put("ha", new GojuonGif("ha", R.raw.gif_ha, R.raw.gif_ha_));
        gifs.put("he", new GojuonGif("he", R.raw.gif_he, R.raw.gif_he_));
        gifs.put("hi", new GojuonGif("hi", R.raw.gif_hi, R.raw.gif_hi_));
        gifs.put("ho", new GojuonGif("ho", R.raw.gif_ho, R.raw.gif_ho_));
        gifs.put("i", new GojuonGif("i", R.raw.gif_i, R.raw.gif_i_));
        gifs.put("ji", new GojuonGif("ji", R.raw.gif_ji, R.raw.gif_ji_));
        gifs.put("ka", new GojuonGif("ka", R.raw.gif_ka, R.raw.gif_ka_));
        gifs.put("ke", new GojuonGif("ke", R.raw.gif_ke, R.raw.gif_ke_));
        gifs.put("ki", new GojuonGif("ki", R.raw.gif_ki, R.raw.gif_ki_));
        gifs.put("ko", new GojuonGif("ko", R.raw.gif_ko, R.raw.gif_ko_));
        gifs.put("ku", new GojuonGif("ku", R.raw.gif_ku, R.raw.gif_ku_));
        gifs.put("ma", new GojuonGif("ma", R.raw.gif_ma, R.raw.gif_ma_));
        gifs.put("me", new GojuonGif("me", R.raw.gif_me, R.raw.gif_me_));
        gifs.put("mi", new GojuonGif("mi", R.raw.gif_mi, R.raw.gif_mi_));
        gifs.put("mo", new GojuonGif("mo", R.raw.gif_mo, R.raw.gif_mo_));
        gifs.put("mu", new GojuonGif("mu", R.raw.gif_mu, R.raw.gif_mu_));
        gifs.put("n", new GojuonGif("n", R.raw.gif_n, R.raw.gif_n_));
        gifs.put("na", new GojuonGif("na", R.raw.gif_na, R.raw.gif_na_));
        gifs.put("ne", new GojuonGif("ne", R.raw.gif_ne, R.raw.gif_ne_));
        gifs.put("ni", new GojuonGif("ni", R.raw.gif_ni, R.raw.gif_ni_));
        gifs.put("no", new GojuonGif("no", R.raw.gif_no, R.raw.gif_no_));
        gifs.put("nu", new GojuonGif("nu", R.raw.gif_nu, R.raw.gif_nu_));
        gifs.put("o", new GojuonGif("o", R.raw.gif_o, R.raw.gif_o_));
        gifs.put("pa", new GojuonGif("pa", R.raw.gif_pa, R.raw.gif_pa_));
        gifs.put("pe", new GojuonGif("pe", R.raw.gif_pe, R.raw.gif_pe_));
        gifs.put("pi", new GojuonGif("pi", R.raw.gif_pi, R.raw.gif_pi_));
        gifs.put("po", new GojuonGif("po", R.raw.gif_po, R.raw.gif_po_));
        gifs.put("pu", new GojuonGif("pu", R.raw.gif_pu, R.raw.gif_pu_));
        gifs.put("ra", new GojuonGif("ra", R.raw.gif_ra, R.raw.gif_ra_));
        gifs.put("re", new GojuonGif("re", R.raw.gif_re, R.raw.gif_re_));
        gifs.put("ri", new GojuonGif("ri", R.raw.gif_ri, R.raw.gif_ri_));
        gifs.put("ro", new GojuonGif("ro", R.raw.gif_ro, R.raw.gif_ro_));
        gifs.put("ru", new GojuonGif("ru", R.raw.gif_ru, R.raw.gif_ru_));
        gifs.put("sa", new GojuonGif("sa", R.raw.gif_sa, R.raw.gif_sa_));
        gifs.put("se", new GojuonGif("se", R.raw.gif_se, R.raw.gif_se_));
        gifs.put("shi", new GojuonGif("shi", R.raw.gif_shi, R.raw.gif_shi_));
        gifs.put("so", new GojuonGif("so", R.raw.gif_so, R.raw.gif_so_));
        gifs.put("su", new GojuonGif("su", R.raw.gif_su, R.raw.gif_su_));
        gifs.put("ta", new GojuonGif("ta", R.raw.gif_ta, R.raw.gif_ta_));
        gifs.put("te", new GojuonGif("te", R.raw.gif_te, R.raw.gif_te_));
        gifs.put("to", new GojuonGif("to", R.raw.gif_to, R.raw.gif_to_));
        gifs.put("tsu", new GojuonGif("tsu", R.raw.gif_tsu, R.raw.gif_tsu_));
        gifs.put("u", new GojuonGif("u", R.raw.gif_u, R.raw.gif_u_));
        gifs.put("wa", new GojuonGif("wa", R.raw.gif_wa, R.raw.gif_wa_));
        gifs.put("wo", new GojuonGif("wo", R.raw.gif_wo, R.raw.gif_wo_));
        gifs.put("ya", new GojuonGif("ya", R.raw.gif_ya, R.raw.gif_ya_));
        gifs.put("yo", new GojuonGif("yo", R.raw.gif_yo, R.raw.gif_yo_));
        gifs.put("yu", new GojuonGif("yu", R.raw.gif_yu, R.raw.gif_yu_));
        gifs.put("za", new GojuonGif("za", R.raw.gif_za, R.raw.gif_za_));
        gifs.put("ze", new GojuonGif("ze", R.raw.gif_ze, R.raw.gif_ze_));
        gifs.put("zi", new GojuonGif("zi", R.raw.gif_zi, R.raw.gif_zi_));
        gifs.put("zo", new GojuonGif("zo", R.raw.gif_zo, R.raw.gif_zo_));
        gifs.put("zu", new GojuonGif("zu", R.raw.gif_zu, R.raw.gif_zu_));
        Log.e(TAG, TAG + "init finish.");
    }

    public GojuonGif getJPGif(String rome) {

        return gifs.get(rome);

    }


}
