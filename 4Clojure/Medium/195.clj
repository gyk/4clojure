(let [p (fn [f n]
          (if (= 0 n) [""]
            (for [i (range n) ch (f f i) sib (f f (- n 1 i))]
              (str \( ch \) sib))))
      p' (memoize p)]
  #(into #{} (p' p' %)))
