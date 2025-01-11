import request from '@/utils/request'

// 查询今日疯抢列表
export function listRush(query) {
  return request({
    url: '/rush/rush/list',
    method: 'get',
    params: query
  })
}

// 查询今日疯抢详细
export function getRush(id) {
  return request({
    url: '/rush/rush/' + id,
    method: 'get'
  })
}

// 新增今日疯抢
export function addRush(data) {
  return request({
    url: '/rush/rush',
    method: 'post',
    data: data
  })
}

// 修改今日疯抢
export function updateRush(data) {
  return request({
    url: '/rush/rush',
    method: 'put',
    data: data
  })
}

// 删除今日疯抢
export function delRush(id) {
  return request({
    url: '/rush/rush/' + id,
    method: 'delete'
  })
}
