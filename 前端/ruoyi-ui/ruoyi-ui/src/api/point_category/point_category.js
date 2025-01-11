import request from '@/utils/request'

// 查询积分商品分类列表
export function listPoint_category(query) {
  return request({
    url: '/point_category/point_category/list',
    method: 'get',
    params: query
  })
}

// 查询积分商品分类详细
export function getPoint_category(pointsCategoryId) {
  return request({
    url: '/point_category/point_category/' + pointsCategoryId,
    method: 'get'
  })
}

// 新增积分商品分类
export function addPoint_category(data) {
  return request({
    url: '/point_category/point_category',
    method: 'post',
    data: data
  })
}

// 修改积分商品分类
export function updatePoint_category(data) {
  return request({
    url: '/point_category/point_category',
    method: 'put',
    data: data
  })
}

// 删除积分商品分类
export function delPoint_category(pointsCategoryId) {
  return request({
    url: '/point_category/point_category/' + pointsCategoryId,
    method: 'delete'
  })
}
