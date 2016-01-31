(fn [x tr]
  (let [abandon-child (fn [[root & offspring] ch]
                        (cons root (remove #(= ch (first %)) offspring)))
        subtree (fn [[root & offspring] ch]
                  (first (filter #(= ch (first %)) offspring)))
        adopt-child #(concat % [%2])
        p-dict
          (loop [d {} forest [tr]]
            (if (empty? forest) d
              (recur
                (reduce #(let [[p & ch] %2] 
                          (if (nil? ch) %
                            (into % (map vector (map first ch) (repeat p)))))
                  d forest)
                (mapcat rest forest))))
        chain (take-while identity (iterate p-dict x))
        top-down
          (fn [tr xs]
            (if (empty? xs) tr
              (let [[x & x_] xs]
                (recur (adopt-child (subtree tr x) (abandon-child tr x)) x_))))]
    (top-down tr (->> chain reverse rest))))
