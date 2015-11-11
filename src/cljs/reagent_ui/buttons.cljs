(ns ui.button
  (:require
      [reagent.core :as reagent :refer [atom]]
      [ui.list :as ul]
    [cljsjs.react :as react]))

(defn basic
  [button-label button-style button-action]
  (reagent/create-class {:reagent-render (fn []
    [:button
      {:type "button" :class button-style :on-click button-action} button-label])}
  ))

(defn submit
  [button-label button-style button-action]
  (reagent/create-class {:reagent-render (fn []
    [:button
      {:type "submit" :class button-style :on-click button-action} button-label])}
  ))

(defn dropdown
  [button-label button-style menu-data]
  (let [is_open? (reagent/atom false)]
    (reagent/create-class {:reagent-render (fn []
      [:div.dropdown
        [:button
          {:type "button" :class button-style :on-click #(reset! is_open? (if @is_open? false true))} button-label]
        (into [:ul {:class (if @is_open? "open" "closed")}]
          (map (fn [data]
            [:li [basic (str (:label data)) "" (:action data)]]) menu-data))])})))

; (defn dropdown
;   [button-label button-style menu-data]
;   (let [is_open? (reagent/atom false)]
;     (reagent/create-class {:reagent-render (fn []
;       [:div.dropdown
;         [:button
;           {:type "button" :class button-style :on-click #(reset! is_open? (if @is_open? false true))} button-label]
;           [ul/menu menu-data @is_open?]])})))
