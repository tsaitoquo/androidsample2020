package jp.quocard.androidsample2020.ui.home

import jp.quocard.androidsample2020.domain.Project

/**
 * @link onClick(Project project) 詳細画面に移動
 */
interface ProjectClickCallback {
    fun onClick(project: Project)
}
