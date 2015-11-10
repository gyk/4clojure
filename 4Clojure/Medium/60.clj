(fn g
  ([f [x & xs]]
      (g f x xs))
  ([f i [x & xs]]
    (cons i
      (if x
        (lazy-seq (g f (f i x) xs))))))
