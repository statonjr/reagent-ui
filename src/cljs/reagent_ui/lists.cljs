(ns ui.list
  (:require [reagent.core :as reagent :refer [atom]]
    [ui.button :as button]
    [cljsjs.react :as react]))

(defn menu
  [list-data list-class]
  (reagent/create-class {:reagent-render (fn []
    [:div [:h1 (str list-class)]
    (into [:ul {:class list-class}]
      (map (fn [data]
        [:li [button/basic (str (:label data)) "" (:action data)]]) list-data))])}
  ))