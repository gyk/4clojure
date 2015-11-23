(fn [ss]
  (let [left-map  (zipmap "([{" (range -3 0))
        right-map (zipmap "}])" (range  1 4))]
    (loop [stack []
           [c & s] ss]
      (if (nil? c)
        (empty? stack)
        (cond
          (left-map c)  (recur (conj stack (left-map c)) s)
          (right-map c) (if (and (not (empty? stack))
                                 (zero? (+ (peek stack) (right-map c))))
                          (recur (pop stack) s)
                          (recur [\ufade] []))
          :else         (recur stack s))))))
