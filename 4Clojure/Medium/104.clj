(fn [dec]
  (let [d->r_f
          (fn [[decem quinque uni]]
            (fn [d]
              (if-let [r ({4 (str uni quinque), 9 (str uni decem)} d)]
                r (apply str (cons (if (>= d 5) quinque "") (repeat (mod d 5) uni))))))
        f-list (map d->r_f (partition 3 2 "**MDCLXVI"))
        digits (->> dec (format "%04d") seq (map (comp read-string str)))]
    (apply str (map #(%1 %2) f-list digits))))
