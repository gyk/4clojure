(fn [b e]
  (let [s (->> b (iterate #(* % %)) (take-while #(<= % e)) (map str) clojure.string/join seq)
        l (count s)
        sq (->> (range) (map #(* % %)) (drop-while #(< % l)) first)
        a (int (Math/sqrt sq))
        s (concat s (repeat (- sq l) \*))
        p (quot (dec a) 2)
        step-f
          (fn f [i y]
            (lazy-cat (mapcat #(repeat i %) (take 2 y)) (f (inc i) (drop 2 y))))
        ds (map #([[0 1] [1 0] [0 -1] [-1 0]] %) (take sq (step-f 1 (cycle [0 1 2 3]))))
        ps (reductions #(map + %1 %2) [p p] ds)
        a- (dec a)
        rot (fn [[r c]] (map + [a- 0] [(- r a-) (- a- r)] [c c]))
        kv (map vector (map rot ps) s)
        A (dec (* 2 a))
        m (atom (vec (repeat A (vec (repeat A \space)))))]
    (doseq [[k v] kv] (swap! m assoc-in k v))
    (mapv #(apply str %) @m)))
