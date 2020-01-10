package jp.quocard.androidsample2020.domain

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    //一覧
    @GET("users/{user}/repos")
    suspend fun getProjectList(@Path("user") user: String): Response<List<Project>>

    //詳細
    @GET("/repos/{user}/{reponame}")
    suspend fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Response<Project>
}