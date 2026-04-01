import Library00.LnStrm.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign07_01 {
//
    public static<T>
    LnStrm<T> mergeLnStrm(LnStrm<LnStrm<T>> fxss, ToIntBiFunction<T,T> cmpr) {
        return new LnStrm<T>(() -> {
            LnStcn<LnStrm<T>> cxss = fxss.eval0();

            while (cxss.consq()) {
                LnStrm<T> fxs = cxss.hd();
                LnStcn<T> cxs = fxs.eval0();

                if (cxs.consq()) {
                    LnStrm<T> gxs = new LnStrm<T>(() -> cxs);

                    return LnStrmSUtil
                        .m2erge0(gxs, mergeLnStrm(cxss.tl(), cmpr), cmpr)
                        .eval0();
                }

                cxss = cxss.tl().eval0();
            }

            return new LnStcn<T>();
        });
    }
} // end of [public class Assign07_01{...}]

