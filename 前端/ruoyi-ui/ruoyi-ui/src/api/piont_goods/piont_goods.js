import request from '@/utils/request'

// 查询积分商品列表
export function listPiont_goods(query) {
  return request({
    url: '/piont_goods/piont_goods/list',
    method: 'get',
    params: query
  })
}

// 查询积分商品详细
export function getPiont_goods(pointsGoodId) {
  return request({
    url: '/piont_goods/piont_goods/' + pointsGoodId,
    method: 'get'
  })
}

// 新增积分商品
export function addPiont_goods(data) {
  return request({
    url: '/piont_goods/piont_goods',
    method: 'post',
    data: data
  })
}

// 修改积分商品
export function updatePiont_goods(data) {
  return request({
    url: '/piont_goods/piont_goods',
    method: 'put',
    data: data
  })
}

// 删除积分商品
export function delPiont_goods(pointsGoodId) {
  return request({
    url: '/piont_goods/piont_goods/' + pointsGoodId,
    method: 'delete'
  })
}
