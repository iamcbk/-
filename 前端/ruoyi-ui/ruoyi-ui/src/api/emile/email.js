import request from '@/utils/request'

// 查询售后邮件数据库列表
export function listEmail(query) {
  return request({
    url: '/emile/email/list',
    method: 'get',
    params: query
  })
}

// 查询售后邮件数据库详细
export function getEmail(emailId) {
  return request({
    url: '/emile/email/' + emailId,
    method: 'get'
  })
}

// 新增售后邮件数据库
export function addEmail(data) {
  return request({
    url: '/emile/email',
    method: 'post',
    data: data
  })
}

// 修改售后邮件数据库
export function updateEmail(data) {
  return request({
    url: '/emile/email',
    method: 'put',
    data: data
  })
}

// 删除售后邮件数据库
export function delEmail(emailId) {
  return request({
    url: '/emile/email/' + emailId,
    method: 'delete'
  })
}
