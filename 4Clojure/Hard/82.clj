(fn [ws]
  (let [wv (vec ws)
        n (count wv)
        connected
          (fn [a b]
            (letfn [(ed=1 [a b]
                (cond
                  (nil? a) (= 1 (count b))
                  (nil? b) (= 1 (count a))
                  :else (let [[a0 & a_] a, [b0 & b_] b]
                          (if (= a0 b0) (ed=1 a_ b_)
                            (or (= a_ b_)
                                (= a  b_)
                                (= a_ b ))))))]
            (ed=1 (seq a) (seq b))))
        degrees
          (->>
            (for [i (range n) j (range (inc i) n)
                    :when (connected (wv i) (wv j))] [i j])
            (apply concat)
            frequencies
            vals
            frequencies)]
    (and (nil? (degrees 0)) (< (degrees 1 0) 3))))
