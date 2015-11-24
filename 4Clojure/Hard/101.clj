(let [ed
      (fn [f a b]
        (let [[a0 & a_] a
              [b0 & b_] b]
          (if (or (nil? a) (nil? b))
            (+ (count a) (count b))
            (if (= a0 b0)
              (f f a_ b_)
              (inc (min (f f a b_)
                        (f f a_ b)
                        (f f a_ b_)))))))
      ed-mem (memoize ed)]
  (partial ed-mem ed-mem))
