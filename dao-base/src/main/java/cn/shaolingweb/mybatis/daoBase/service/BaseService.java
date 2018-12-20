package cn.shaolingweb.mybatis.daoBase.service;

import org.mybatis.spring.SqlSessionTemplate;

import java.io.Serializable;
import java.util.List;


/**
 * 完成通用的增删改查
 */
public interface BaseService<T, PK extends Serializable> {

    int insert(T obj) throws RuntimeException;

    /**
     * 批量插入
     * @param domainList 需要更新的集合
     * @param count      多少笔数据提交一次
     */
    int insertBatch(Class<T> cls, List<T> domainList, Integer count);

    /**
     * 批量插入,基本原生SQL的数据库插入
     */
    int insertBatch(Class<T> cls, List<T> domainList);

    /**
     * 按条件查询实体,不分页
     * @param obj 不能为空
     * @return
     */
    List<T> findByCondition(T obj);

    /**
     * 按条件查询实体并分页
     */
    List<T> findByCondition(T obj, int start, int limit);

    /**
     * 分页查询
     * @param obj   查询对象
     * @param pager 分页对象
     * @return 查询结果
     */
    List<T> findByCondition(T obj, Pager pager);

    /**
     * 按主键查询
     */
    T findByPK(PK pk, Class<T> cls);

    /**
     * 更新实体
     */
    int update(T obj);

    /**
     * 按查询条件更新实体，不局限于依据主键更新
     *
     * @param queryObj  查询条件
     * @param updateObj 更新对象
     * @return The number of rows affected by the update.
     */
    int updateByCondition(Class<T> cls, T queryObj, T updateObj, List<String> ignoreProperList);

    /**
     * 按主键删除实体
     */
    int delete(PK pk, Class<T> cls);

    /**
     * 按条件删除
     *
     */
    int deleteByCondition(T obj);

    /**
     * 根据ID串，进行批量删除
     *
     * @param ids id字符串  如: 1,2,3
     * @param cls 实体类型
     */
    int deleteByIds(String ids, Class<T> cls);

    /**
     * 按条件查询总记录数
     */
    Integer getTotalCount(T object);

    /**
     * 批量更新
     * @param domainList 需要更新的集合
     * @param count      多少笔数据提交一次,0采用默认
     */
    int updateBatch(Class<T> cls, List<T> domainList, int count);
    int updateBatch(Class<T> cls, List<T> domainList);

    SqlSessionTemplate getCurSqlSessionTemplate();

    class Pager implements Serializable {
        private static final long serialVersionUID = -1886255970252256895L;
        /* 当前页号 */
        private int currentPage = 1;
        /* 起止页码 */
        private int startNum = 0;
        private int endNum = 10;
        /* 每页记录数 */
        private int pageSize = 10;
        /* 结果集起始记录数 */
        private int startRow;
        /* 总计页数 */
        private int totalPages;
        /* 总计记录数 */
        private int totalRows;
        private List<?> data;

        public Pager() {
        }

        /**
         * 通过记录总数和每页记录数计算出相关信息
         *
         * @param totalRows 记录总数
         * @param pageSize  每页记录数
         */
        public Pager(int totalRows, int pageSize) {
            setPageSize(pageSize);
            this.totalRows = totalRows;
            totalPages = this.totalRows / this.pageSize;
            int mod = this.totalRows % this.pageSize;
            if (mod > 0) {
                totalPages++;
            }
            if (this.totalRows == 0) {
                currentPage = 0;
            } else {
                currentPage = 1;
            }
            startRow = 0;
            if (this.totalRows == 0) {
                startNum = 0;
            } else {
                startNum = 1;
            }
            if (this.totalRows <= this.pageSize) {
                endNum = this.totalRows;
            } else {
                if (currentPage == totalPages) {
                    endNum = this.totalRows;
                } else {
                    endNum = this.pageSize * currentPage;
                }
            }
        }

        /**
         * 通过记录总数和每页记录数计算出相关信息
         *
         * @param totalRows 记录总数
         * @param pageSize  每页记录数
         */
        public Pager(int totalRows, int pageSize, int currentPage) {

            setPageSize(pageSize);
            this.totalRows = totalRows;
            totalPages = this.totalRows / this.pageSize;

            int mod = this.totalRows % this.pageSize;
            if (mod > 0) {
                totalPages++;
            }
            if (totalPages < currentPage) {
                currentPage = totalPages;
            }
            if (currentPage < 1) {
                currentPage = 1;
            }
            if (this.totalRows == 0) {
                this.currentPage = 1;
            } else {
                this.currentPage = currentPage;
            }
            startRow = 0;
            if (this.totalRows == 0) {
                startNum = 0;
            } else {
                startNum = (currentPage - 1) * pageSize;
            }
            if (this.totalRows <= this.pageSize) {
                endNum = this.totalRows;
            } else {
                if (currentPage == totalPages) {
                    endNum = this.totalRows;
                } else {
                    endNum = this.pageSize * currentPage;
                }
            }
        }

        /**
         * 第一页
         */
        public void first() {
            if (totalRows == 0) {
                currentPage = 0;
            } else {
                currentPage = 1;
            }
            startRow = 0;
            if (totalRows == 0) {
                startNum = 0;
            } else {
                startNum = 1;
            }
            if (totalPages <= pageSize) {
                endNum = totalRows;
            } else {
                if (currentPage == totalPages) {
                    endNum = totalRows;
                } else {
                    endNum = pageSize * currentPage;
                }
            }
        }

        /**
         * 最后一页
         */
        public void last() {
            if (totalPages != 0) {
                currentPage = totalPages;
                startRow = (currentPage - 1) * pageSize;
                startNum = startRow + 1;
                endNum = totalRows;
            }
        }

        /**
         * 下一页
         */
        public void next() {

            if (currentPage < totalPages && totalPages != 0) {
                currentPage++;
            }
            if (totalPages != 0) {
                startRow = (currentPage - 1) * pageSize;
            }
            startNum = startRow + 1;
            if (currentPage < totalPages && totalPages != 0) {
                endNum = startRow + pageSize;
            } else {
                endNum = totalRows;
            }
        }

        /**
         * 上一页
         */
        public void previous() {
            if (currentPage <= 1) {
                first();
            } else {
                currentPage--;
                startRow = (currentPage - 1) * pageSize;
                startNum = startRow + 1;
                if (currentPage < totalPages && totalPages != 0) {
                    endNum = startRow + pageSize;
                } else {
                    endNum = totalRows;
                }
            }
        }

        /**
         * 更新页
         */
        public void refresh(int currentPage) {
            this.currentPage = currentPage;
            if (this.currentPage > totalPages) {
                last();
            }
            if (this.currentPage < 0) {
                first();
            }
        }

        /**
         * 指定页码
         */
        public void go(int pageNo) {
            if (pageNo <= 1) {
                first();
            } else if (pageNo >= totalPages) {
                last();
                if (totalPages < 1) {
                    currentPage = 0;
                }
            } else {
                startRow = (currentPage - 1) * pageSize;
                startNum = startRow + 1;
                endNum = startRow + pageSize;
            }
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getStartRow() {
            return startRow;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getTotalRows() {
            return totalRows;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
        }

        public int getEndNum() {
            return endNum;
        }

        public void setEndNum(int endNum) {
            this.endNum = endNum;
        }

        public int getStartNum() {
            if (startNum < 0)
                startNum = 0;
            return startNum;
        }

        public void setStartNum(int startNum) {
            this.startNum = startNum;
        }

        public List<?> getData() {
            return data;
        }

        public void setData(List<?> data) {
            this.data = data;
        }

    }
}
