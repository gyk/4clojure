(fn [a]
  (let [len (count (str a))
        [half-len r] ((juxt quot rem) len 2)
        b (quot a (int (Math/pow 10 (- len half-len r))))
        prnt
          (fn [k is-odd]
            (let [[l m] ((juxt quot rem) k 10)
                  [l m] [(if (zero? l) "" (str l)) (str m)]]
              (read-string (str l m (if is-odd "" m) (clojure.string/reverse l)))))
        gen-par (fn f [k is-odd]
                  (cons (prnt k is-odd)
                    (lazy-seq
                      (let [k (inc k)]
                        (if (->> k str (re-matches #"10+"))
                          (if is-odd
                            (f (/ k 10) false)
                            (f k true))
                          (f k is-odd))))))]
    (filter #(>= % a) (gen-par b (= r 1)))))
