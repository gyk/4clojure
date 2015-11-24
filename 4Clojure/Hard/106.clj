(fn [s t]
  (let [f (fn [s]
            (set (mapcat #(if (integer? %) [(* % 2) (/ % 2) (+ % 2)]) s)))]
    (->> #{s} (iterate f) (take-while #(not (% t))) count inc)))
