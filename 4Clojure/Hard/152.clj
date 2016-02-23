(fn [s]
  (let [c (map count s)
        nc (apply max c)
        cart (fn cart [ns]
              (if (empty? ns) '(())
                (for [x (range (first ns))
                      xs (cart (rest ns))]
                  (cons x xs))))
        sq (let [m (map #(inc (- nc %)) c)]
              (for [l (cart m) :let [r (map - (repeat nc) l c)]]
                (map concat (map #(repeat % :P) l) s
                            (map #(repeat % :P) r))))
        latin? (fn [s]
                  (let [n (count s)
                        x (set (apply concat s))
                        ? #(= x (set %))]
                    (and (= n (count x)) (not (x :P)) (every? ? s)
                         (every? ? (apply map vector s)))))]
    (->>
      (for [n (range 2 (inc (min (count s) nc)))
            x sq
            :let [p #(partition n 1 %)]
            b (mapcat #(apply map vector (map p %)) (p x))
            :when (latin? b)] [n b])
      (into #{}) (map first) frequencies)))
