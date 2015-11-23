(fn [x]
  (if (#{0 1 2} x) false
    (let [n (* 2 x)
          sqrt-n (-> n Math/sqrt Math/ceil int)
          p (loop [a (-> n (repeat true) vec transient)
                   i 2]
              (cond
                (> i sqrt-n) (persistent! a)
                (a i)
                  (recur (reduce #(assoc! % %2 false) a (range (+ i i) n i)) (inc i))
                :else (recur a (inc i))))]
      (if (p x)
        (let [-+  (fn [f]
                    (loop [i x]
                      (let [i (f i)]
                        (if (p i) i (recur i)))))
              p-- (-+ dec)
              p++ (-+ inc)]
          (-> (+ p-- p++) (/ 2) (= x)))
        false))))
