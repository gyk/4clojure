(fn f [k xs]
  (if (< (count xs) k) ()
    (cons (take k xs) (lazy-seq (f k (drop k xs))))))
