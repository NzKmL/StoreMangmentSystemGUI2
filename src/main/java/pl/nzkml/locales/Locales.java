package pl.nzkml.locales;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Locales {
    private final String POLISH = "Polski";
    private final String ENGLISH = "English";

    private Map<String, Locale> map = new HashMap<>();
    private Locale currentLocate=null;
    private static Locales instance = null;

    public static Locales getInstance(){
        if(instance==null){
            instance=new Locales();
        }
        return instance;
    }

    private Locales(){
        map.put(POLISH, new Locale("pl"));
        map.put(ENGLISH, new Locale("en"));
        currentLocate = map.get(POLISH);
    }

    public Set<String> getLanguages(){
        return map.keySet();
    }
    public Locale getLocaleByLanguage(String language){
        return map.get(language);
    }

    public String getCurrentLanguage(){
        if ("pl".equals(currentLocate.toString())) {
            return POLISH;
        } else if ("en".equals(currentLocate.toString())) {
            return ENGLISH;
        }
        return POLISH;
    }

    public void setCurrentLocate(Locale currentLocate) {
        this.currentLocate = currentLocate;
    }

    public Locale getCurrentLocate() {
        return currentLocate;
    }
}
