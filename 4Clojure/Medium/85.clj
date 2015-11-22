(fn [s]
  (let [n
          (count s)
        bin->r-selector
          (fn [b]
            (loop [selector [], b b]
              (if (= 0 b)
                selector
                (recur (conj selector (-> b (bit-and 1) zero? not)) (bit-shift-right b 1)))))
        r-select
          (fn [r-sq r-selector]
            (-> (map #(when %1 %2) r-selector r-sq) set (disj nil)))
        pow-set
          (for [i (range (bit-shift-left 1 n))]
            (r-select s (bin->r-selector i)))]
    (set pow-set)))
