(fn [x tr]
  (let [p-dict
          (loop [d {} forest [tr]]
            (if (empty? forest) d
              (recur (into d
                        (for [t forest offs (rest t)] [(first offs) (first t)]))
                (mapcat rest forest))))]
    (loop [[r & offs :as t] tr
           [c & c_] (->> x (iterate p-dict) (take-while identity) reverse rest)]
      (if (nil? c) t
        (let [in-path? #(= c (first %))
              rest-tree (cons r (remove in-path? offs))
              sub-tree (first (filter in-path? offs))]
          (recur (concat sub-tree [rest-tree]) c_))))))
