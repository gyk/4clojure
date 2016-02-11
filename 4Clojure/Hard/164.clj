(fn [{s0 :start, E :accepts, T :transitions}]
  (let [tr (fn [[s p]] (for [[a s'] (T s)] [s' (conj p a)]))]
    (loop [m #{} sp [[s0 []]] l #{}]
      (let [m' (map first sp)]
        (if (every? m m') l
          (recur (into m m') (mapcat tr sp) 
            (into l (for [[s p] sp :when (E s)] (apply str p)))))))))
